package com.chanchuan.data;

import java.util.List;

public class InfoBean {

    public String status;
    public List<DataBean> datas;

    public class DataBean {
        public String thumbnail;
        public String title;
        public String author;
        public String description;
    }
}
