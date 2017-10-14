#!/bin/sh 

# param
imgFrom=$1
imgTo=$2
echo "$imgFrom -> $imgTo "

width=`/usr/local/bin/identify -format '%W' $imgFrom`
height=`/usr/local/bin/identify -format '%H' $imgFrom`
width=`expr $width / 3`
height=`expr $height / 3`
echo "$width x $height"


# command composite
shellCmd="convert "${imgFrom}" \"/data/cdn/resource/logo.png["${width}"x"${height}"<]\" -gravity northeast -geometry +24+20 -composite jpg:- | convert -quality 75 - "${imgTo}
echo $shellCmd

# run cmd
eval $shellCmd
echo "finish wxh.sh"
