package com.hpe.findlover.model;

import javax.persistence.*;

public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String image1;

    private String image2;

    private String image3;

    private String image4;

    private String image5;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return image1
     */
    public String getImage1() {
        return image1;
    }

    /**
     * @param image1
     */
    public void setImage1(String image1) {
        this.image1 = image1;
    }

    /**
     * @return image2
     */
    public String getImage2() {
        return image2;
    }

    /**
     * @param image2
     */
    public void setImage2(String image2) {
        this.image2 = image2;
    }

    /**
     * @return image3
     */
    public String getImage3() {
        return image3;
    }

    /**
     * @param image3
     */
    public void setImage3(String image3) {
        this.image3 = image3;
    }

    /**
     * @return image4
     */
    public String getImage4() {
        return image4;
    }

    /**
     * @param image4
     */
    public void setImage4(String image4) {
        this.image4 = image4;
    }

    /**
     * @return image5
     */
    public String getImage5() {
        return image5;
    }

    /**
     * @param image5
     */
    public void setImage5(String image5) {
        this.image5 = image5;
    }
}