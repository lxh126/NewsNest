package com.app.luoxueheng;

//import com.fasterxml.jackson.annotation.JsonInclude;
//import com.fasterxml.jackson.core.type.TypeReference;
//import com.fasterxml.jackson.databind.DeserializationFeature;
//import com.fasterxml.jackson.databind.JavaType;
//import com.fasterxml.jackson.databind.ObjectMapper

import java.io.Serializable;
import java.util.List;

//import lombok.Data;
//import lombok.NoArgsConstructor;

//@NoArgsConstructor
//@Data
public class NewsInfo {


    private String pageSize;
    private int total;
    private List<DataDTO> data;
    private String currentPage;

    public String getPageSize() {
        return pageSize;
    }

    public void setPageSize(String pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<DataDTO> getData() {
        return data;
    }

    public void setData(List<DataDTO> data) {
        this.data = data;
    }

    public String getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(String currentPage) {
        this.currentPage = currentPage;
    }

    public static class DataDTO implements Serializable {
        private String image;
        private int imagenum;
        private String publishTime;
        private List<KeywordsDTO> keywords;
        private String language;
        private String video;
        private String title;
        private List<WhenDTO> when;
        private String content;
        private String openRels;
        private String url;
        private List<PersonsDTO> persons;
        private String newsID;
        private String crawlTime;
        private List<OrganizationsDTO> organizations;
        private String publisher;
        private List<LocationsDTO> locations;
        private List<?> where;
        private List<?> scholars;
        private String category;
        private List<WhoDTO> who;


        private static String replaceFirst(String ret, String strToReplace, String replaceWithThis) {
            return ret.replaceFirst(strToReplace, replaceWithThis);
        }

        //去掉最后面的一个字符
        public static String replaceLast(String text, String strToReplace, String replaceWithThis) {
            return text.replaceFirst("(?s)" + strToReplace + "(?!.*?" + strToReplace + ")", replaceWithThis);
        }
        public int getImagenum() {
            String[] arr1 =image.split(",");
            //System.out.println(arr1.length);
            return arr1.length;
        }

        public void setImagenum(int imagenum) {
            this.imagenum = imagenum;
        }


        public String getImage(int i) {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getrealimage(int i){
            int num=getImagenum();
            if(num==0){
                return "";
            } else{
                int len=image.length();
                String tmp=image.substring(1,len-1);
                String arr2[]=tmp.split(",");
                if(i!=0){
                    arr2[i]=arr2[i].substring(1);
                }
                return arr2[i];
            }
        }

        public String getPublishTime() {
            return publishTime;
        }

        public void setPublishTime(String publishTime) {
            this.publishTime = publishTime;
        }

        public List<KeywordsDTO> getKeywords() {
            return keywords;
        }

        public void setKeywords(List<KeywordsDTO> keywords) {
            this.keywords = keywords;
        }

        public String getLanguage() {
            return language;
        }

        public void setLanguage(String language) {
            this.language = language;
        }

        public String getVideo() {
            return video;
        }

        public void setVideo(String video) {
            this.video = video;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public List<WhenDTO> getWhen() {
            return when;
        }

        public void setWhen(List<WhenDTO> when) {
            this.when = when;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getOpenRels() {
            return openRels;
        }

        public void setOpenRels(String openRels) {
            this.openRels = openRels;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public List<PersonsDTO> getPersons() {
            return persons;
        }

        public void setPersons(List<PersonsDTO> persons) {
            this.persons = persons;
        }

        public String getNewsID() {
            return newsID;
        }

        public void setNewsID(String newsID) {
            this.newsID = newsID;
        }

        public String getCrawlTime() {
            return crawlTime;
        }

        public void setCrawlTime(String crawlTime) {
            this.crawlTime = crawlTime;
        }

        public List<OrganizationsDTO> getOrganizations() {
            return organizations;
        }

        public void setOrganizations(List<OrganizationsDTO> organizations) {
            this.organizations = organizations;
        }

        public String getPublisher() {
            return publisher;
        }

        public void setPublisher(String publisher) {
            this.publisher = publisher;
        }

        public List<LocationsDTO> getLocations() {
            return locations;
        }

        public void setLocations(List<LocationsDTO> locations) {
            this.locations = locations;
        }

        public List<?> getWhere() {
            return where;
        }

        public void setWhere(List<?> where) {
            this.where = where;
        }

        public List<?> getScholars() {
            return scholars;
        }

        public void setScholars(List<?> scholars) {
            this.scholars = scholars;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public List<WhoDTO> getWho() {
            return who;
        }

        public void setWho(List<WhoDTO> who) {
            this.who = who;
        }
        public String getTextandimage(){
            return content+image;
        }

//        public void setTextandimage(String textandimage) {
//            this.Textandimage = textandimage;
//        }

        public static class  KeywordsDTO implements Serializable {
            private float score;
            private String word;

            public float getScore() {
                return score;
            }

            public void setScore(float score) {
                this.score = score;
            }

            public String getWord() {
                return word;
            }

            public void setWord(String word) {
                this.word = word;
            }
        }

        public static class WhenDTO implements Serializable{
            private float score;
            private String word;

            public float getScore() {
                return score;
            }

            public void setScore(float score) {
                this.score = score;
            }

            public String getWord() {
                return word;
            }

            public void setWord(String word) {
                this.word = word;
            }
        }

        public static class PersonsDTO implements Serializable {
            private int count;
            private String linkedURL;
            private String mention;

            public int getCount() {
                return count;
            }

            public void setCount(int count) {
                this.count = count;
            }

            public String getLinkedURL() {
                return linkedURL;
            }

            public void setLinkedURL(String linkedURL) {
                this.linkedURL = linkedURL;
            }

            public String getMention() {
                return mention;
            }

            public void setMention(String mention) {
                this.mention = mention;
            }
        }

        public static class OrganizationsDTO implements Serializable{
            private int count;
            private String linkedURL;
            private String mention;

            public int getCount() {
                return count;
            }

            public void setCount(int count) {
                this.count = count;
            }

            public String getLinkedURL() {
                return linkedURL;
            }

            public void setLinkedURL(String linkedURL) {
                this.linkedURL = linkedURL;
            }

            public String getMention() {
                return mention;
            }

            public void setMention(String mention) {
                this.mention = mention;
            }
        }

        public static class LocationsDTO implements Serializable{
            private int count;
            private String linkedURL;
            private String mention;

            public int getCount() {
                return count;
            }

            public void setCount(int count) {
                this.count = count;
            }

            public String getLinkedURL() {
                return linkedURL;
            }

            public void setLinkedURL(String linkedURL) {
                this.linkedURL = linkedURL;
            }

            public String getMention() {
                return mention;
            }

            public void setMention(String mention) {
                this.mention = mention;
            }
        }

        public static class WhoDTO implements Serializable{
            private float score;
            private String word;

            public float getScore() {
                return score;
            }

            public void setScore(float score) {
                this.score = score;
            }

            public String getWord() {
                return word;
            }

            public void setWord(String word) {
                this.word = word;
            }
        }
    }
}
