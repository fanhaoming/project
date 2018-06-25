package com.project.core.base.page;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.project.core.base.enums.OrderType;

import java.util.List;
import java.util.Map;

/**
 * @ClassName BaseSearcher
 * @Description TODO
 * @Author fanhaoming
 * @Date 2018/6/7  9:21
 * @Version 1.0
 **/
public class Pager {

    private List<SearchCondition> searchConditions;

    private Map<String,OrderType> orderBy;

    // 页数（第几页）
    private int currentpage;

    // 查询数据库里面对应的数据有多少条
    private int total;// 从数据库查处的总记录数

    // 每页查询的数量条
    private int size;

    // 下页
    @JsonProperty(access = Access.READ_ONLY)
    private int next;

    // 最后一页
    @JsonProperty(access = Access.READ_ONLY)
    private int last;

    @JsonProperty(access = Access.READ_ONLY)
    private int lpage;

    @JsonProperty(access = Access.READ_ONLY)
    private int rpage;

    //从哪条开始查
    @JsonProperty(access = Access.READ_ONLY)
    private int start;

    public int getCurrentpage() {
        return currentpage;
    }

    /****
     *
     * @param total   总记录数
     * @param currentpage    当前页
     * @param pagesize    每页显示多少条
     */
    public void init(int total,int currentpage,int pagesize) {
        //总记录数
        this.total = total;
        //每页显示多少条
        this.size=pagesize;

        this.currentpage = currentpage;

        //计算当前页和数据库查询起始值以及总页数
        setCurrentpage();

        //分页计算
        int leftcount =5,    //需要向上一页执行多少次
                rightcount =4;
        //起点页
        this.lpage =currentpage;
        //结束页
        this.rpage =currentpage;

        //2点判断
        this.lpage = currentpage-leftcount;            //正常情况下的起点
        this.rpage = currentpage+rightcount;        //正常情况下的终点

        //页差=总页数和结束页的差
        int topdiv = this.last-rpage;                //判断是否大于最大页数

        /***
         * 起点页
         * 1、页差<0  起点页=起点页+页差值
         * 2、页差>=0 起点和终点判断
         */
        this.lpage=topdiv<0? this.lpage+topdiv:this.lpage;

        /***
         * 结束页
         * 1、起点页<=0   结束页=|起点页|+1
         * 2、起点页>0    结束页
         */
        this.rpage=this.lpage<=0? this.rpage+(this.lpage*-1)+1: this.rpage;

        /***
         * 当起点页<=0  让起点页为第一页
         * 否则不管
         */
        this.lpage=this.lpage<=0? 1:this.lpage;

        /***
         * 如果结束页>总页数   结束页=总页数
         * 否则不管
         */
        this.rpage=this.rpage>last? this.last:this.rpage;
    }


    public void setCurrentpage() {
        //如果整除表示正好分N页，如果不能整除在N页的基础上+1页
        int totalPages = total%size==0? total/size : (total/size)+1;

        //总页数
        this.last = totalPages;

        //判断当前页是否越界,如果越界，我们就查最后一页
        if(currentpage>totalPages){
            this.currentpage = totalPages;
        }else{
            this.currentpage=currentpage;
        }
        if(currentpage<=0){
            this.currentpage=1;
        }

        //计算start   1----0    2  ------ 5
        this.start = (this.currentpage-1)*size;

    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getNext() {
        return  currentpage<last? currentpage+1: last;
    }


    //上一页
    public int getUpper() {
        return currentpage>1? currentpage-1: currentpage;
    }

    public int getLast() {
        return last;
    }

    //总共有多少页，即末页
    public void setLast(int last) {
        this.last = total%size==0? total/size : (total/size)+1;
    }


    public int getLpage() {
        return lpage;
    }

    public void setLpage(int lpage) {
        this.lpage = lpage;
    }

    public int getRpage() {
        return rpage;
    }

    public void setRpage(int rpage) {
        this.rpage = rpage;
    }

    public int getStart() {
        return start;
    }

    public void setNext(int next) {
        this.next = next;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public List<SearchCondition> getSearchConditions() {
        return searchConditions;
    }

    public void setSearchConditions(List<SearchCondition> searchConditions) {
        this.searchConditions = searchConditions;
    }

    public Map<String, OrderType> getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(Map<String, OrderType> orderBy) {
        this.orderBy = orderBy;
    }

    public void setCurrentpage(int currentpage) {
        this.currentpage = currentpage;
    }
}
