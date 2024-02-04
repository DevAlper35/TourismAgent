package business;

import core.Helper;
import dao.RoomDao;
import entity.Pension;
import entity.Room;
import entity.User;

import java.util.ArrayList;

public class RoomManager {
    RoomDao roomDao = new RoomDao();

    public Room getById(int id){return this.roomDao.getByID(id);}
    public ArrayList<Room> findAll(){return this.roomDao.findAll();}

    public ArrayList<Object[]> getForTable(int size,ArrayList<Room> rooms){
        ArrayList<Object[]> roomList = new ArrayList<>();
        for (Room obj : rooms){
            int i = 0;
            Object[] rowObject = new Object[size];

            rowObject[i++] = obj.getId();
            rowObject[i++] = obj.getHotel_id();
            rowObject[i++] = obj.getPension_id();
            rowObject[i++] = obj.getSeason_id();
            rowObject[i++] = obj.getType();
            rowObject[i++] = obj.getStock();
            rowObject[i++] = obj.getAdult_price();
            rowObject[i++] = obj.getChild_price();
            rowObject[i++] = obj.getBed_capacity();
            rowObject[i++] = obj.getSquare_meter();
            rowObject[i++] = obj.isTelevision();
            rowObject[i++] = obj.isMinibar();
            rowObject[i++] = obj.isGame_console();
            rowObject[i++] = obj.isCash_box();
            rowObject[i++] = obj.isProjection();
            roomList.add(rowObject);
        }
        return roomList;
    }
    public boolean save(Room room){
        if(room.getId()!=0){
            Helper.showMsg("error");
        }
        return this.roomDao.save(room);
    }

    public boolean delete(int id){
        if(this.getById(id) == null){
            Helper.showMsg(id + " ID kayıtlı model bulunamadı");
            return false;
        }
        return this.roomDao.delete(id);
    }
    public boolean update(Room room) {
        if (this.getById(room.getId()) == null) {
            Helper.showMsg(room.getId() + "ID kayıtlı model bulunamadı");
            return false;
        }
        return this.roomDao.update(room);
    }
}
