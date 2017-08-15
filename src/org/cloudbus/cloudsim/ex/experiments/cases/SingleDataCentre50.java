package org.cloudbus.cloudsim.ex.experiments.cases;

import java.io.IOException;

import org.cloudbus.cloudsim.ex.experiments.ExperimentsUtil;
import org.cloudbus.cloudsim.ex.experiments.SingleDatacentre;

public class SingleDataCentre50 extends SingleDatacentre {

    public SingleDataCentre50() {
	numOfSessions = 50;
    }

    /**
     * @param args
     * @throws IOException
     */
    public static void main(final String[] args) throws IOException {
	ExperimentsUtil.parseExperimentParameters(args);
	new SingleDataCentre50().runExperimemt();
    }

}
