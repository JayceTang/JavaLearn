package com.jayce.multithread.travelbookportalweb;

import java.util.*;
import java.util.concurrent.*;

/**
 * @author jayce tang
 * @version 1.0
 * @ClassName TravelBool  旅行指南
 * @description     旅游路线预定网站
 * @date 2019/5/2 16:25
 */
public class TravelBook {

    private final ExecutorService exec = new ThreadPoolExecutor(20,20,0,TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>());

    private class QuoteTask implements Callable<TravelQuote> {

        private final TravelCompany company;
        private final TravelInfo info;
        private final TravelQuote DEFAULT_TQ = new TravelQuote();

        public QuoteTask(TravelCompany company, TravelInfo info) {
            this.company = company;
            this.info = info;
        }

        @Override
        public TravelQuote call() throws Exception {
            return company.solicitQuote(info);
        }

        public TravelQuote getFailureQuote(Throwable cause) {
            return DEFAULT_TQ;
        }

        public TravelQuote getTimeoutQuote(CancellationException e) {
            return DEFAULT_TQ;
        }
    }

    public List<TravelQuote> getRankedTraveQuotes(TravelInfo travelInfo, Set<TravelCompany> companies,
                                                  Comparable<TravelQuote> ranking, long time, TimeUnit unit) throws InterruptedException {
        List<QuoteTask> tasks = new ArrayList<>();
        for (TravelCompany company : companies) {
            tasks.add(new QuoteTask(company, travelInfo));
        }

        List<Future<TravelQuote>> futures = exec.invokeAll(tasks, time, unit);

        List<TravelQuote> quotes = new ArrayList<>(tasks.size());
        Iterator<QuoteTask> taskIterator = tasks.iterator();
        for (Future<TravelQuote> future : futures) {
            QuoteTask task = taskIterator.next();
            try {
                quotes.add(future.get());
            } catch (ExecutionException e) {
                quotes.add(task.getFailureQuote(e.getCause()));
            } catch (CancellationException e){
                quotes.add(task.getTimeoutQuote(e));
            }
        }

        Collections.sort(quotes, null);
        return quotes;

    }
}
