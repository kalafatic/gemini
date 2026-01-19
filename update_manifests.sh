#!/bin/bash

for dir in eu.kalafatic.gemini.*/ ; do
  if [ -f "$dir/META-INF/MANIFEST.MF" ]; then
    sed -i 's/Bundle-Version: .*/Bundle-Version: 2.6.1.qualifier/' "$dir/META-INF/MANIFEST.MF"
  fi
done
