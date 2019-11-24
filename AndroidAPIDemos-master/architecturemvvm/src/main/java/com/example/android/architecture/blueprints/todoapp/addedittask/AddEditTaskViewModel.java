package com.example.android.architecture.blueprints.todoapp.addedittask;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;

import com.example.android.architecture.blueprints.todoapp.data.Task;
import com.example.android.architecture.blueprints.todoapp.data.source.TasksRepository;
import com.google.common.base.Strings;
import com.lll.architecturemvvm.R;

import rx.Completable;
import rx.Observable;
import rx.Observer;
import rx.subjects.PublishSubject;

import static com.google.common.base.Preconditions.checkNotNull;


/**
 * Version 1.0
 * Created by lll on 10/25/17.
 * Description
 * copyright generalray4239@gmail.com
 */
public class AddEditTaskViewModel {


    @NonNull
    private final TasksRepository mTasksRepository;/*数据来源*/

    @NonNull
    private final AddEditTaskNavigator mNavigator;

    // using a PublishSubject because we are not interested in the last object that was emitted
    // before subscribing. Like this we avoid displaying the snackbar multiple times
    @NonNull
    private final PublishSubject<Integer> mSnackbarText;

    private String mTaskId;
    private String mRestoredTitle;
    private String mRestoredDescription;

    public AddEditTaskViewModel(@Nullable String taskId, @NonNull TasksRepository tasksRepository,
                                @NonNull AddEditTaskNavigator navigator) {
        mTasksRepository = checkNotNull(tasksRepository, "TaskRepository cannot be null");
        mNavigator = checkNotNull(navigator, "navigator cannot be null");
        mTaskId = taskId;
        mSnackbarText = PublishSubject.create();
    }

    /**
     * @return a stream that emits when a snackbar should be displayed. The stream contains the
     * snackbar text
     */
    @NonNull
    public Observable<Integer> getSnackbarText() {
        return mSnackbarText.asObservable();
    }


    /**
     * @return a stream containing the model for the UI.
     */
    @NonNull
    public Observable<AddEditTaskUiModel> getUiModel() {
        if (Strings.isNullOrEmpty(mTaskId)) {
            // new task. nothing to do here.
            return Observable.empty();
        }
        return mTasksRepository.getTask(mTaskId)
                .map(this::restoreTask)
                .doOnError(__ -> showSnackbar(R.string.empty_task_message))
                .map(task -> new AddEditTaskUiModel(task.getTitle(), task.getDescription()));
    }

    /**
     * Sets the restored state.
     *
     * @param title       the restored title.
     * @param description the restored description.
     */
    public void setRestoredState(@Nullable String title, @Nullable String description) {
        mRestoredTitle = title;
        mRestoredDescription = description;
    }

    private Task restoreTask(Task task) {
        String title = mRestoredTitle != null ? mRestoredTitle : task.getTitle();
        String description = mRestoredDescription != null ? mRestoredDescription : task.getDescription();

        return new Task(title, description, task.getId());
    }

    /**
     * Save a task (create if it's a new task or update if task exists) with a title and a description
     *
     * @param title       title of the task
     * @param description description of the task
     */
    @NonNull
    public Completable saveTask(String title, String description) {
        return createTask(title, description)
                .doOnCompleted(mNavigator::onTaskSaved);
    }

    private boolean isNewTask() {
        return mTaskId == null;
    }

    private Completable createTask(String title, String description) {
        Task newTask;
        if (isNewTask()) {
            newTask = new Task(title, description);
            if (newTask.isEmpty()) {
                showSnackbar(R.string.empty_task_message);
                return Completable.complete();
            }
        } else {
            newTask = new Task(title, description, mTaskId);
        }
        return mTasksRepository.saveTask(newTask);
    }

    private void showSnackbar(@StringRes int textId) {
        mSnackbarText.onNext(textId);
    }


}
