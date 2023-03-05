#!/bin/zsh

cd extensions/sudocode
npx vsce package
code --uninstall-extension ttm.sudocode
code --install-extension *.vsix