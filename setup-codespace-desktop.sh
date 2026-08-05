#!/usr/bin/env bash
set -euo pipefail

# setup-codespace-desktop.sh
# Installs XFCE, VNC, and optional noVNC for the current Ubuntu Codespace container.

if [ "$(id -u)" -ne 0 ]; then
  echo "This script must be run as root or with sudo."
  exit 1
fi

export HOME=/home/codespace
export DISPLAY=:1
export XVFB_LOG=/tmp/xvfb-setup.log
export X11VNC_LOG=/tmp/x11vnc-setup.log
export XFCE_LOG=/tmp/xfce-setup.log
export NOVNC_LOG=/tmp/novnc-setup.log

echo "Installing desktop packages..."
apt-get update
apt-get install -y xfce4 xfce4-goodies x11vnc xvfb dbus-x11 git

mkdir -p "$HOME/.vnc"
chown codespace:codespace "$HOME/.vnc"
chmod 700 "$HOME/.vnc"

# Create VNC password non-interactively if VNC_PASSWORD is set, otherwise reuse an existing password file.
if [ -n "${VNC_PASSWORD:-}" ]; then
  echo "Creating VNC password from VNC_PASSWORD environment variable..."
  x11vnc -storepasswd "$VNC_PASSWORD" /home/codespace/.vnc/passwd
  chown codespace:codespace /home/codespace/.vnc/passwd
  chmod 600 /home/codespace/.vnc/passwd
else
  if [ ! -f "$HOME/.vnc/passwd" ]; then
    echo "No VNC password found. Please set VNC_PASSWORD or run x11vnc -storepasswd manually."
    echo "Example: VNC_PASSWORD=secret sudo ./setup-codespace-desktop.sh"
    exit 1
  fi
fi

# Start Xvfb if not already running.
if ! pgrep -f "Xvfb :1" >/dev/null 2>&1; then
  echo "Starting Xvfb on display $DISPLAY..."
  Xvfb "$DISPLAY" -screen 0 1280x800x24 -nolisten tcp >"$XVFB_LOG" 2>&1 &
  sleep 2
fi

# Start XFCE session if not already running.
if ! pgrep -f "xfce4-session" >/dev/null 2>&1; then
  echo "Starting XFCE session on display $DISPLAY..."
  export DISPLAY="$DISPLAY"
  export XAUTHORITY="$HOME/.Xauthority"
  su - codespace -c "DISPLAY=$DISPLAY dbus-launch startxfce4" >"$XFCE_LOG" 2>&1 &
  sleep 4
fi

# Start x11vnc if not running.
if ! pgrep -f "x11vnc -display :1" >/dev/null 2>&1; then
  echo "Starting x11vnc on display $DISPLAY, port 5901..."
  x11vnc -display "$DISPLAY" -forever -shared -rfbauth "$HOME/.vnc/passwd" -rfbport 5901 >"$X11VNC_LOG" 2>&1 &
  sleep 2
fi

# Install and start noVNC if not already set up.
NOVNC_DIR="$HOME/noVNC"
if [ ! -d "$NOVNC_DIR" ]; then
  echo "Cloning noVNC..."
  su - codespace -c "git clone https://github.com/novnc/noVNC.git $NOVNC_DIR"
fi

if ! pgrep -f "novnc_proxy --vnc localhost:5901" >/dev/null 2>&1; then
  echo "Starting noVNC proxy on port 6901..."
  su - codespace -c "cd $NOVNC_DIR && ./utils/novnc_proxy --vnc localhost:5901 --listen 6901" >"$NOVNC_LOG" 2>&1 &
  sleep 2
fi

cat <<'EOF'

Desktop setup complete.

Next steps:
1. In Codespaces, forward port 5901 for VNC.
2. Optionally forward port 6901 for browser access via noVNC.
3. Connect with a VNC client to localhost:5901 or open the forwarded noVNC URL.

If you're using the script with a password, set VNC_PASSWORD before running:
  sudo VNC_PASSWORD=YourSecretPassword ./setup-codespace-desktop.sh

Log files:
  $XVFB_LOG
  $XFCE_LOG
  $X11VNC_LOG
  $NOVNC_LOG
EOF
