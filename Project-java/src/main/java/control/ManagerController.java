package control;

import model.DAO.ImplementUserCovid;
import model.DAO.UserCovidModel;
import model.User;
import model.UserCovid;
import utils.dbUtil;
import model.ListUserCovid;
import view.ViewManager;

public class ManagerController {
    ImplementUserCovid implementUserCovid;

    public ManagerController() {
        implementUserCovid = new UserCovidModel();
        
        // implementUserCovid.add(new UserCovid());
    }
}
