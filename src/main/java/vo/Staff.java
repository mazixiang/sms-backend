package vo;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;

public class Staff {
    private int id;
    private String name;
    private String password;
    private String gender;
    private int age;
    private String contactInfo;
    private String hobbies;
    private String department;

    public static Staff jsonStringToStaff(String jsonString) {
        System.out.println(jsonString);
        Staff res = new Staff();
        JSONObject staffJsonObject = JSONUtil.parseObj(jsonString);
        if (staffJsonObject.get("id") != null) {
            res.setId(Integer.parseInt((String) staffJsonObject.get("id")));
        } else {
            res.setId(0);
        }
        res.setName((String) staffJsonObject.get("name"));
        res.setPassword((String) staffJsonObject.get("password"));
        res.setGender((String) staffJsonObject.get("gender"));
        res.setAge((int) staffJsonObject.get("age"));
        res.setContactInfo((String) staffJsonObject.get("contactInfo"));
        res.setDepartment((String) staffJsonObject.get("department"));
        res.setHobbies((String) staffJsonObject.get("hobbies"));
        return res;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    public String getHobbies() {
        return hobbies;
    }

    public void setHobbies(String hobbies) {
        this.hobbies = hobbies;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public JSONObject toJsonObject() {
        JSONObject staffJson = new JSONObject();
        staffJson.put("id", this.getId())
                .put("name", this.getName())
                .put("password", this.getPassword())
                .put("gender", this.getGender())
                .put("age", this.getAge())
                .put("contactInfo", this.getContactInfo())
                .put("hobbies", this.getHobbies())
                .put("department", this.getDepartment());
        return staffJson;
    }
}
