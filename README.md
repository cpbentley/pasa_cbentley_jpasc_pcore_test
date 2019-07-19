# pasa_cbentley_jpasc_pcore_test
JUnit testing of pasa_cbentley_jpasc_pcore
project id | pasa_cbentley_jpasc_pcore_test
------------ | -------------
author | Charles-Philip Bentley
license | ![license MIT](https://img.shields.io/badge/license-MIT-yellowgreen.svg?style=plastic)
tags | Java Ant
created | June 2019
modified | ![GitHub last commit](https://img.shields.io/github/last-commit/cpbentley/pasa_cbentley_jpasc_pcore_test.svg?style=plastic)

Quote by Chuck Norris
> A lot of times people look at the negative side of what they feel they can't do. I always look on the positive side of what I can do.

# Hello Ctx

Test the build process with core.src4 and ant.

## Table of Contents
  * [Installation](#installation)
  * [Eclipse Run](#run-within-eclipse)

***

## Installation

### How to Run / Build

[//]: # (This may be the most platform independent comment)

#### Setup <a name="setup"></a>

* Install Java 8 **JDK** (author used 191). You need the Java _Development_ Kit, which includes the Runtime (JRE). [Oracle JDK 8 Download Page](https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html "Lastest JDK 8 from Oracle")
* Install Eclipse (author used 2019-06). [Eclipse Download Page](https://www.eclipse.org/downloads/ "https://www.eclipse.org/downloads/")
* When launching Eclipse, create a workspace or use an existing workspace where you will check out all the github repos.

* An Eclipse workspace compiles continuously all its projects and their files. Errors are reported in the problems view.

* Import the github repositories listed below. Following the given order, you should not see any compilation errors.

  1. Right click on link and copy link to clipboard
  2. In Eclipse, use the menu File -> Import
  ![eclipse_run_as.jpg](/res/tutorial/eclipse_import_git.jpg)
  3. In the dialog find Git -> Projects from Git -> Clone URI
  4. If you have copied the github repository url in the clipboard, you should see
    ![eclipse_run_as.jpg](/res/tutorial/eclipse_git_cloneuri.jpg)
  5. Select master branch
   ![eclipse_run_as.jpg]
   (https://github.com/cpbentley/pasa_cbentley_app_hello_ctx/blob/master/res/tutorial/eclipse_git_branch.jpg?raw=true)
  6. Next-Next
  7. It should detect an Eclipse Project.

* Import the final link (actual project of this repository) using the same process.
* Optional: Check the source code.

#### Github Repository Dependencies

num | id | Right Click -> Copy Link Address
----| -- | -------------
1 | pasa_cbentley_core_src4 | [Import Link](https://github.com/cpbentley/pasa_cbentley_core_src4)
final | pasa_cbentley_app_hello_ctx | [Import Link](https://github.com/cpbentley/pasa_cbentley_app_hello_ctx)


#### Run within Eclipse
[Up](#table-of-contents)
In Eclipse Project **_pasa_cbentley_app_hello_ctx_**, src folder, look up a Java file with the following method

```java
public static void main(String[] args) {

}
```

Right click on it and select Run As

![eclipse_run_as.jpg](/res/tutorial/eclipse_run_as.jpg "Optional title")

 #### Build a Jar with Ant

Those instructions can be followed by those who want to build an executable jar file.

Once built, jar can be launched using the command line
```
java -jar pathtojarfile.jar
```
Or by double clicking on the file (Windows)

##### Instructions

* ![build.security](https://img.shields.io/badge/build-security-red.svg) The Build process is always a weak link. Sure the source is open but if the build process is corrupted, the builder can insert anything in the final executable of the program.

* The build process here uses Ant from Eclipse. Some people complain Ant is old. It has advantages. To my experience and knowledge its not backdoored, its extensible and you control every step of the build process. Author does not trust magic. There is no free lunch.

* Author uses a custom build script using proguard and a preprocessor to optimize away debug statements

*  Author's responsibility is to provide the complete source code.

  1. antenna-simplepreprocessor1.1-bin-with-src.jar

  2. proguard-5.3.3-bin-with-src.jar


* User responsibility.

    1. Make sure Eclipse and Ant shipping with it are \<legit\>.
    2. If your security protocol requires it, inspect and rebuild the jars in build-libs folder.

## Tasks List

- [x] Pages Github
- [ ] Optimize ant build file
- [ ] Test process by external user on Linux
- [ ] Test process by external user on Mac

[table of contents](#table-of-contents)

## Links

 * [Proguard Official Sourceforge Repository](https://sourceforge.net/projects/proguard/ "Proguard on sourceforge.net")
 * [Antenna](http://antenna.sourceforge.net/wtkpreprocess.php)
 * [Apache Ant](https://ant.apache.org)


[table of contents](#table-of-contents)

