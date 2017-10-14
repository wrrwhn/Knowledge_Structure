#!/bin/bash

# default path
sourcepath=/data/tmp/yao/from
dstpath=/data/tmp/yao/to

for file in `ls -a $sourcepath`
do
	curpath="$sourcepath/$file"
	echo "$curpath"	
	
	if [ -f "$curpath" ] 
	then
		width=`/usr/local/bin/identify -format '%W' $curpath`
		height=`/usr/local/bin/identify -format '%H' $curpath`
		echo "			--> $width x $height"
		width=`expr $width / 3`
		height=`expr $height / 3`
		echo "			--> $width x $height"

		# do it
		cmd="/usr/local/bin/convert $sourcepath/$file \"/data/cdn/resource/logo.png[$width x $height<]\" -gravity southeast -geometry +20+20 -composite jpg:- | /usr/local/bin/convert -quality 75 - $dstpath/preview-$file"
		echo "$cmd"
		res=`$cmd`
	fi
done
