package com.rabbitmq.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionSynchronizationAdapter;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Administrator on 2017/5/10.
 */
@Component
public class AfterCommitExecutor extends TransactionSynchronizationAdapter implements CommitExecutor {
    private static final Logger LOGGER = LoggerFactory.getLogger(AfterCommitExecutor.class);
    private static final ThreadLocal<List<Runnable>> RUNNABLES = new ThreadLocal<List<Runnable>>();
    private ExecutorService threadPool = Executors.newFixedThreadPool(5);
    @Override
    public void execute(Runnable command) {

    }
}
