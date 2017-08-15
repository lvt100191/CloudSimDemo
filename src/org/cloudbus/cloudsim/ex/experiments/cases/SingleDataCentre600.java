package org.cloudbus.cloudsim.ex.experiments.cases;

import java.io.IOException;

import org.cloudbus.cloudsim.ex.experiments.ExperimentsUtil;
import org.cloudbus.cloudsim.ex.experiments.SingleDatacentre;

public class SingleDataCentre600 extends SingleDatacentre {

    public SingleDataCentre600() {
	numOfSessions = 600;
    }

    /**
     * @param args
     * @throws IOException
     */
    public static void main(final String[] args) throws IOException {
	ExperimentsUtil.parseExperimentParameters(args);
	new SingleDataCentre600().runExperimemt();
    }

}
