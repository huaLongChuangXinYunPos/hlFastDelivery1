package zhaoq.hl.fastdelivery.callback;

import zhaoq.hl.fastdelivery.task.TaskResult;

/**
 * PACKAGE_NAME:zhaoq.hl.fastdelivery.task
 * CREATE_BY:zhaoqiang
 * AUTHOR_EMAIL:zhaoq_hero@163.com
 * DATE: 2016/03/21  11:02
 */

/**
 * 所有异步任务的  回调接口
 */
public interface TaskCallback {

    void onTaskFinished(TaskResult result);

}
