package org.cloudbus.cloudsim.ex.experiments.cases;

import java.io.IOException;

import org.cloudbus.cloudsim.ex.experiments.ExperimentsUtil;
import org.cloudbus.cloudsim.ex.experiments.SingleDatacentre;

public class SingleDataCentre25 extends SingleDatacentre {

    public SingleDataCentre25() {
	numOfSessions = 25;
    }

    /**
     * @param args
     * @throws IOException
     */
    public static void main(final String[] args) throws IOException {
	ExperimentsUtil.parseExperimentParameters(args);
	new SingleDataCentre25().runExperimemt();
    }

}
