package sqlClientApp;

import java.awt.*;

public class Constraints {

    private GridBagConstraints constraints;

    public Constraints(){
        this.constraints = new GridBagConstraints();
    }

    public void setConstraintsForDBPanel(){
        this.constraints.gridx = 0;
        this.constraints.gridy = 0;
        this.constraints.weightx = 1.0;
        this.constraints.weighty = 1.0;
        this.constraints.fill = GridBagConstraints.BOTH;
    }

    public void setConstraintsForCommandPanel(){
        this.constraints.fill = GridBagConstraints.BOTH;
        this.constraints.gridx = 2;
    }

    public void setConstraintsForResultPanel(){
        this.constraints.fill = GridBagConstraints.BOTH;
        this.constraints.gridx = 0;
        this.constraints.gridy = 2;
        this.constraints.gridwidth = 3;
        this.constraints.gridheight = 2;
    }

    public GridBagConstraints getConstraint(){
        return this.constraints;
    }


}
