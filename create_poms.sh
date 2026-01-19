#!/bin/bash

for dir in eu.kalafatic.gemini.*/ ; do
  if [ -f "$dir/plugin.xml" ]; then
    PACKAGING="eclipse-plugin"
  elif [ -f "$dir/feature.xml" ]; then
    PACKAGING="eclipse-feature"
  elif [ -f "$dir/META-INF/MANIFEST.MF" ]; then
    if grep -q "Fragment-Host" "$dir/META-INF/MANIFEST.MF"; then
      PACKAGING="eclipse-plugin"
    else
      PACKAGING="eclipse-plugin"
    fi
  else
    continue
  fi

  ARTIFACT_ID=$(basename "$dir")

  cat > "$dir/pom.xml" <<EOF
<?xml version="1.0" encoding="UTF-8"?>
<project xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd" xmlns="http://maven.apache.org/POM/4.0.0"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">
  <modelVersion>4.0.0</modelVersion>
  <parent>
    <groupId>eu.kalafatic.gemini</groupId>
    <artifactId>parent</artifactId>
    <version>2.6.1-SNAPSHOT</version>
    <relativePath>../pom.xml</relativePath>
  </parent>
  <artifactId>${ARTIFACT_ID}</artifactId>
  <packaging>${PACKAGING}</packaging>
</project>
EOF

done
