package com.logistics.entity;

/**
 * Created by Mklaus on 15/8/21.
 */
public class Report extends BasicEntity{
    /**
     * 总支出
     */
    private int output;

    /**
     * 总收入
     */

    private int input;

    /**
     * 总利润
     */
    private int profit;


    /**
     * 公司运营任务收入
     */
    private int inputOfTask;

    /**
     * 公司外包承运收入
     */
    private int inputOfCarry;

    /**
     * 物资购买支出
     */

    private int outputOfGoodsBuy;

    /**
     * 员工工资
     */

    private int outputOfSalary;

    /**
     * 燃油支出
     */

    private int outputOfOil;

    public Report() {
    }

    public int getOutput() {
        return this.outputOfGoodsBuy + this.outputOfOil + this.outputOfSalary;
    }

    public int getInput() {
        return this.inputOfTask + this.inputOfCarry;
    }

    public int getProfit() {
        return this.getInput() - this.getOutput();
    }

    public int getInputOfTask() {
        return inputOfTask;
    }

    public void setInputOfTask(int inputOfTask) {
        this.inputOfTask = inputOfTask;
    }

    public int getInputOfCarry() {
        return inputOfCarry;
    }

    public void setInputOfCarry(int inputOfCarry) {
        this.inputOfCarry = inputOfCarry;
    }

    public int getOutputOfGoodsBuy() {
        return outputOfGoodsBuy;
    }

    public void setOutputOfGoodsBuy(int outputOfGoodsBuy) {
        this.outputOfGoodsBuy = outputOfGoodsBuy;
    }

    public int getOutputOfSalary() {
        return outputOfSalary;
    }

    public void setOutputOfSalary(int outputOfSalary) {
        this.outputOfSalary = outputOfSalary;
    }

    public int getOutputOfOil() {
        return outputOfOil;
    }

    public void setOutputOfOil(int outputOfOil) {
        this.outputOfOil = outputOfOil;
    }
}
