package org.firstinspires.ftc.library.component;

import org.firstinspires.ftc.library.IsaacBot;

/**
 *
 */
public class RobotComponent extends Component {

    /**
     * @param robot
     */
    public RobotComponent(IsaacBot robot) {

        super(robot);
    }

    /**
     *
     */
    public void init () {
        super.init();

        //this.getCommandQueue().setDebug(true);
    }
}