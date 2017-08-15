package org.cloudbus.cloudsim.ex.experiments.cases;

import java.io.IOException;

import org.cloudbus.cloudsim.ex.experiments.ExperimentsUtil;
import org.cloudbus.cloudsim.ex.experiments.SingleDatacentre;

public class SingleDataCentre75 extends SingleDatacentre {

    public SingleDataCentre75() {
	numOfSessions = 75;
    }

    /**
     * @param args
     * @throws IOException
     */
    public static void main(final String[] args) throws IOException {
	ExperimentsUtil.parseExperimentParameters(args);
	new SingleDataCentre75().runExperimemt();
    }

}
