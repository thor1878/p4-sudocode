#!/bin/zsh

cd src/grammar
antlr4 SudoLexer.g4 -o ../dist
antlr4 SudoParser.g4 -o ../dist
cd ../..
javac -cp lib/*.jar -d bin src/(dist|helpers)/*.java src/App.java 
