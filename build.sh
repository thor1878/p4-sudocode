#!/bin/zsh

cd src/grammar
java -cp ../../lib/antlr-4.12.0-complete.jar org.antlr.v4.Tool SudoLexer.g4 -o ../dist
java -cp ../../lib/antlr-4.12.0-complete.jar org.antlr.v4.Tool SudoParser.g4 -o ../dist
cd ../..
javac -cp lib/*.jar -d bin src/(dist|helpers)/*.java src/App.java 


mkdir -p bin/input-files/
cp src/input-files/* bin/input-files/