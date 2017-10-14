#!/bin/sh 
convert /data/tmp/yao/from/yk-white.jpg "/data/cdn/resource/logo.png" -gravity northeast -geometry +24+20 -composite jpg:- | convert -quality 75 - /data/tmp/yao/to/preview-yk-white-1.jpg
convert /data/tmp/yao/from/yk-black.jpg "/data/cdn/resource/logo.png" -gravity northeast -geometry +24+20 -composite jpg:- | convert -quality 75 - /data/tmp/yao/to/preview-yk-black-1.jpg
convert /data/tmp/yao/from/yk-red.jpg "/data/cdn/resource/logo.png" -gravity northeast -geometry +24+20 -composite jpg:- | convert -quality 75 - /data/tmp/yao/to/preview-yk-red-1.jpg
convert /data/tmp/yao/from/yk-brown.jpg "/data/cdn/resource/logo.png" -gravity northeast -geometry +24+20 -composite jpg:- | convert -quality 75 - /data/tmp/yao/to/preview-yk-brown-1.jpg


convert /data/tmp/yao/from/yk-white.jpg "/data/cdn/resource/logo-2.png" -gravity northeast -geometry +24+20 -composite jpg:- | convert -quality 75 - /data/tmp/yao/to/preview-yk-white-2.jpg
convert /data/tmp/yao/from/yk-black.jpg "/data/cdn/resource/logo-2.png" -gravity northeast -geometry +24+20 -composite jpg:- | convert -quality 75 - /data/tmp/yao/to/preview-yk-black-2.jpg
convert /data/tmp/yao/from/yk-red.jpg "/data/cdn/resource/logo-2.png" -gravity northeast -geometry +24+20 -composite jpg:- | convert -quality 75 - /data/tmp/yao/to/preview-yk-red-2.jpg
convert /data/tmp/yao/from/yk-brown.jpg "/data/cdn/resource/logo-2.png" -gravity northeast -geometry +24+20 -composite jpg:- | convert -quality 75 - /data/tmp/yao/to/preview-yk-brown-2.jpg
