package com.yao.study.designPatterns.packages;

/**
 * Created by Yao on 2015/3/17.
 */
public class StereoOffWithCDCommand implements Command {

    Stereo stereo;

    public StereoOffWithCDCommand(Stereo stereo) {
        this.stereo = stereo;
    }

    @Override
    public void execute() {
        stereo.off();
    }
}
