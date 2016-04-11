package zhaoq.hl.fastdelivery.task;

/**
 * PACKAGE_NAME:zhaoq.hl.fastdelivery.task
 * CREATE_BY:zhaoqiang
 * AUTHOR_EMAIL:zhaoq_hero@163.com
 * DATE: 2016/03/21  11:03
 * 异步任务  回调结果的  封装类
 */
public class TaskResult {

    public int task_id; //异步任务的   返回标识

    public int resultStatus = -1;//默认返回的结果码  失败数据

    public Object data = null;//返回数据   默认没有数据

}
