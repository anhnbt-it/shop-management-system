package model.admin;

import java.util.Date;

public class Category {
    private int id;
    private String name;
    private String desc;
    private String img;
    private Date created;
    private int type_id;

    public Category(int id, String name, String desc, String img, Date created, int type_id) {
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.img = img;
        this.created = created;
        this.type_id = type_id;
    }

    public Category(String name, String desc, String img, int type_id) {
        this.name = name;
        this.desc = desc;
        this.img = img;
        this.type_id = type_id;
    }

    public Category(int id, String name, String desc, String img, int type_id) {
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.img = img;
        this.type_id = type_id;
    }

    public Category() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public int getType_id() {
        return type_id;
    }

    public void setType_id(int type_id) {
        this.type_id = type_id;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                ", img='" + img + '\'' +
                ", created=" + created +
                ", type_id=" + type_id +
                '}';
    }
}
