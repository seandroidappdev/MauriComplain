package androidhive.info.materialdesign.model;

import java.util.HashMap;

/**
 * Created by Fazley on 09/06/2015.
 */
public class ComplainAppController {

    public static ComplainAppController controller;
    public static HashMap<Integer,Complain> complains = new HashMap<>();

    public static final String DEPT_INFRASTRUCTURE = "infrastructure";
    public static final String DEPT_LEGAL = "legal";

    private ComplainAppController(){}

    public static ComplainAppController getInstance() {
        if(controller != null)
            return controller;
        else
            controller = new ComplainAppController();
        return controller;
    }

}
