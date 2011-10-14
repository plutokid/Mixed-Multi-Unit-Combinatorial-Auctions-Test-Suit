package com.mmuca.expLab.domain.distributions;

import java.util.Random;

abstract public class MarkovDistribution implements ITargetedDistribution {
    protected Parameters parameters;
    protected ValueRange range;
    protected int initialValue;
    protected Random randomGenerator;

    public MarkovDistribution(ValueRange range, Parameters parameters) {
        this.range = range;
        this.randomGenerator = new Random();
        this.parameters = parameters;
        this.initialValue =range.getStart();
    }

    public void setTarget(int initialValue) {
       this.initialValue =initialValue;
    }

    public int flipCoin() {
        int currentValue = this.initialValue;
        while (changeValue()){
            currentValue= nextValueTo(currentValue);
        }
        return currentValue;
    }

    private boolean changeValue() {
        boolean changeValue = randomGenerator.nextDouble() < parameters.probabilityOfChangingValue;
        return changeValue;
    }

    protected int nextValueTo(int value) {
        if (value == range.getStart())  return value+1;
        if (value == range.getEnd())    return value-1;
        return switchDirection() ? reverseStep(value): directStep(value);
    }

    protected boolean switchDirection() {
        return (randomGenerator.nextDouble() < parameters.probabilityOfSwitchingDirection);
    }

    protected abstract int directStep(int value);
    protected abstract int reverseStep(int value);

    public static class Parameters{
        private double probabilityOfChangingValue;
        private double probabilityOfSwitchingDirection;

        public Parameters(double probabilityOfChangingValue, double probabilityOfSwitchingDirection) {
            this.probabilityOfSwitchingDirection = probabilityOfSwitchingDirection;
            this.probabilityOfChangingValue = probabilityOfChangingValue;
        }

        public double getProbabilityOfSwitchingDirection() {
            return probabilityOfSwitchingDirection;
        }

        public double getProbabilityOfChangingValue() {
            return probabilityOfChangingValue;
        }
   }
}
