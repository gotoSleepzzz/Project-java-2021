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
        UserCovid user = new UserCovid("abc","123456789011",1990,"Thành phố Hồ Chí Minh, Quận Bình Thạnh, Phường 05","F1",20,"123456789010");
        var x = implementUserCovid.getUserCovidByID("123456789001");
        System.out.println(x.toString());
    }
}
