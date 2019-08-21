package com.jayce.multithread.chapter10;

import javax.naming.InsufficientResourcesException;

/**
 * @author Jayce Tang
 * @version 1.0
 * @date 2019/8/19 15:43
 * @description     里面包含10-2及10-3的例子
 *  10-2说明为何看着代码无死锁为何确存在死锁的隐患，
 *  10-3说明如何解决这种由于业务顺序的问题导致的死锁
 */
public class OrderDeadLockAndFixed{
    /** 10-2  动态的锁顺序死锁！ （错误示范）
     * 该方法如果只有一个账号在运行时，运行不会出现任何问题，
     * 但如果账号同时进行转账而进行账号锁定时，那么久可能会发生死锁的问题！
     */
    public void transferMoney1(Account fromAccount, Account toAccount, DollarAmount amount) throws InsufficientResourcesException {
        synchronized (fromAccount) {
            synchronized (toAccount) {
                if (fromAccount.getBalance().compareTo(amount) < 0) {
                    throw new InsufficientResourcesException();
                } else {
                    fromAccount.debit(amount);
                    toAccount.credit(amount);
                }
            }
        }
    }

    /**
     * 10-3 通过锁顺序来避免死锁
     * 该版本的方法是用system.identifyHashCode来定义锁的顺序，消除了发生死锁发生的可能性！！！
     * @param fromAccount
     * @param toAccount
     * @param amount
     * @throws InsufficientResourcesException
     */
    private static final Object tieLock = new Object();
    public void transferMoney2(final Account fromAccount, final Account toAccount, final DollarAmount amount) throws InsufficientResourcesException {
        class Helper {
            public void transfer() throws InsufficientResourcesException {
                if (fromAccount.getBalance().compareTo(amount) < 0) {
                    throw new InsufficientResourcesException();
                } else {
                    fromAccount.debit(amount);
                    toAccount.credit(amount);
                }
            }
        }
        //  对比他们的hashCode,让他们按顺序执行，避免了顺序执行不一致而导致可能发生的死锁问题
        //  但还可能发生hashCode相同的情况，那么就设置一个全局的tieLock，只有也只能有一个执行程序能先拥有该锁，那么就避免了该死锁问题，
        //  但因为多加一把锁影响性能，所以只有在极端的情况下（即发生hashCode相同的情况）才使用这把锁（tieLock）
        int fromHash = System.identityHashCode(fromAccount);
        int toHash = System.identityHashCode(toAccount);

        if (fromHash < toHash) {
            synchronized (fromAccount) {
                synchronized (toAccount) {
                    new Helper().transfer();
                }
            }
        } else if (fromHash > toHash) {
            synchronized (toAccount) {
                synchronized (fromAccount) {
                    new Helper().transfer();
                }
            }
        } else {
            synchronized (tieLock) {
                synchronized (fromAccount) {
                    synchronized (toAccount) {
                        new Helper().transfer();
                    }
                }
            }
        }
    }

    private static class Account {

        public Comparable<DollarAmount> getBalance() {
            return new Comparable<DollarAmount>() {
                @Override
                public int compareTo(DollarAmount o) {
                    return 0;
                }
            };
        }

        public void debit(DollarAmount amount) {
        }

        public void credit(DollarAmount amount) {
        }
    }

    private static class DollarAmount {

    }
}

