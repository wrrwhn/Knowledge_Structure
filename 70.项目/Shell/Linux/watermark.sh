#!/bin/sh 

# param
imgFrom="/data/cdn/yao/from/slide-rId2.jpg"
imgTo="/data/cdn/yao/to/preview-slide-rId2.jpg"
watermark="/data/cdn/yao/from/watermark-basic-blue.png"

# params
if [ X"$1" != X ]
then
	imgFrom=$1
fi
if [ X"$2" != X ]
then
	imgTo=$2
fi
if [ X"$3" != X ]
then
	watermark=$3
fi

width=`/usr/local/bin/identify -format '%W' $imgFrom`
height=`/usr/local/bin/identify -format '%H' $imgFrom`
width=`expr $width / 4`
height=`expr $height / 4`
echo "$width x $height"

 
# command composite
shellCmd="convert "${imgFrom}" \"$watermark["${width}"x"${height}"<]\"  -gravity northeast -geometry +20+20 -composite jpg:- | /usr/local/bin/convert -quality 75 - "${imgTo}
echo $shellCmd

# run cmd
eval $shellCmd
echo "watermark($imgFrom, $watermark)[$width X $height] -> $imgTo "

