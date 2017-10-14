#!/bin/sh 
fromFolder= "/data/tmp/yao/from"
for filePath in ${fromFolder}
	filename= `basename $filePath`
	/usr/local/bin/composite -tile /data/tmp/yao/resource/repeat-4.png ${filename} jpg:- | /usr/local/bin/convert - "/data/tmp/yao/resource/logo-2.png[182x27]" -gravity northeast -geometry +5+10 -composite jpg:- | /usr/local/bin/convert -quality 75 - /data/tmp/yao/to/${filename}
	echo $filename + "convert finish"
done