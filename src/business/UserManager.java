package business;

import core.Helper;
import dao.UserDao;
import entity.User;

import java.util.ArrayList;

public class UserManager {
    private final UserDao userDao;

    public UserManager() {
        this.userDao = new UserDao();
    }

    public User findByLoging(String username, String password) {
        // Farklı işlemler yapabiliriz ...
        return this.userDao.findByLogin(username, password);

    }

    public ArrayList<Object[]> getForTable(int size, ArrayList<User> modelList) {
        ArrayList<Object[]> modelObjList = new ArrayList<>();
        for (User obj : modelList) {
            int i = 0;
            Object[] rowObject = new Object[size];
            rowObject[i++] = obj.getId();
            rowObject[i++] = obj.getUsername();
            rowObject[i++] = obj.getPassword();
            rowObject[i++] = obj.getRole();
            modelObjList.add(rowObject);
        }
        return modelObjList;

    }

    public ArrayList<User> findAll() {
        return this.userDao.findAll();
    }

    public boolean save(User user) {
        if (this.getById(user.getId()) != null) {
            Helper.showMsg("error");
            return false;
        }
        return this.userDao.save(user);
    }

    public User getById(int id) {
        return this.userDao.getByID(id);
    }
    public boolean delete(int id){  // DELETE İŞLEMİ
        if(this.getById(id) == null){
            Helper.showMsg(id + " ID kayıtlı model bulunamadı");
            return false;
        }
        return this.userDao.delete(id);
    }

}