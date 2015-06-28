#!/bin/sh

#. /etc/global.rc

export LANG=en_US.UTF-8

echo "java start"
cd /home/nannohi/dev

date

ant clean
ant
#ant run
java -cp ./build:./lib/* myclass.MyTweet $1 $2
#java -cp ./build:./lib/* myclass.MyTest
