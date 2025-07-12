exec: build
	java -cp bin Main

build:
	javac -d bin/ $(shell find src -name "*.java")