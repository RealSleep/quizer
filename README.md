# quizer

Simple quiz program for command-line interface (CLI).
You have test and you want take quiz with that. Run it with "quizer".
Now "quizer" support two type of questions. They are multiple choice (MCQ) and fill-in questions.
Question starts with description and followed by right answer (if it MCQ continue with options) then ends
with new line.

## Installation

This project used the maven building tool. (if you want just to run program you can go to usage section)
To build the project you need to install a maven in your local machine. (maven[https://maven.apache.org/download.cgi?Preferred=ftp://mirror.reverse.net/pub/apache/])
Run

```bash
mvn package
```

The command line will printing end with the following:

```bash
 ...
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  <TIME>s
[INFO] Finished at: yyyy-MM-dd'T'HH:mm:ss.SSSZ
[INFO] ------------------------------------------------------------------------
```

And that's it you build project.

## Usage

You built the project now time to run it.

```bash
java -jar target/quizer-1.0-SNAPSHOT.jar <filePath>
```

For a start you can use 'target/classes/JavaQuiz.txt' file.

