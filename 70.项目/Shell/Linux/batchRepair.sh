#!/bin/bash

# default path
filepath=/data/tmp/yao/from
dirpath=res/image

# replace path from param
if [ X"$1" != X ]
then
	filepath=$1
fi

echo "-->"${filepath}

# list file
curCnt=0
for file in `ls -a $filepath`
do
	fileCnt=`ls -a $filepath | wc -l`
	curCnt=`expr $curCnt + 1`

	if [ x"$file" != x"." -a x"$file" != x".." ];then  
		curpath="$filepath/$file/$dirpath"
		echo "	-->($curCnt/$fileCnt) $curpath"

		# check dir
		if [ -d "$curpath" ] 
		then
			sliderCnt=`ls $curpath | grep "^slide-rId\w*.jpg" | wc -l`
			previewCnt=`ls $curpath | grep "^preview-slide-rId\w*.jpg" | wc -l`
			# check basic-file/conveted
		        if [ $sliderCnt -gt 0 ] # && [ $previewCnt -le 0 ] 
		        then
	        		for slidejpg in `ls $curpath | grep "^slide-rId\w*.jpg"`
		        	do
		        		echo "		--> $curpath/$slidejpg"

	        			# calc w/h
					width=`/usr/local/bin/identify -format '%W' $curpath/$slidejpg`
					height=`/usr/local/bin/identify -format '%H' $curpath/$slidejpg`
					echo "			--> $width x $height"
					width=`expr $width / 4`
					height=`expr $height / 4`
					echo "			--> $width x $height"
					# do it
					`/usr/local/bin/convert $curpath/$slidejpg "/data/cdn/resource/logo.png[$width x $height<]" -gravity southeast -geometry +20+20 -composite jpg:- | /usr/local/bin/convert -quality 75 - $curpath/preview-$slidejpg`
		        	done
			else
				echo "		--> no slide-rId\w*.jpg"
		        fi
		
	        fi  
	fi  
done
