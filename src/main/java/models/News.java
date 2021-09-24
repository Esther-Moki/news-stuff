package models;

import java.util.Objects;

public class News {
    private String topic;
    private String content;
    private int departmentsId;
    private int usersId;
    private int id;

    public News(String topic,String content,int departmentsId,int usersId){
        this.topic = topic;
        this.content = content;
        this.departmentsId = departmentsId;
        this.usersId = usersId;

    }

    public String getTopic() {
        return topic;
    }

    public int getDepartmentsId() {
        return departmentsId;
    }

    public void setDepartmentsId(int departmentsId) {
        this.departmentsId = departmentsId;
    }

    public int getUsersId() {
        return usersId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUsersId(int usersId) {
        this.usersId = usersId;
    }

    public String getContent() {
        return content;
    }


    public void setContent(String content) {
        this.content = content;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Review)) return false;
        Review review = (Review) o;
        return rating == review.rating &&
                id == review.id &&
                restaurantId == review.restaurantId &&
                Objects.equals(content, review.content) &&
                Objects.equals(writtenBy, review.writtenBy);
    }

    @Override
    public int hashCode() {
        return Objects.hash(content, writtenBy, rating, id, restaurantId);
    }
}
