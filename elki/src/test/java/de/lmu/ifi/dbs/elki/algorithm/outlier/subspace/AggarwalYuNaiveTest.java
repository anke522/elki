package de.lmu.ifi.dbs.elki.algorithm.outlier.subspace;

/*
 This file is part of ELKI:
 Environment for Developing KDD-Applications Supported by Index-Structures

 Copyright (C) 2015
 Ludwig-Maximilians-Universität München
 Lehr- und Forschungseinheit für Datenbanksysteme
 ELKI Development Team

 This program is free software: you can redistribute it and/or modify
 it under the terms of the GNU Affero General Public License as published by
 the Free Software Foundation, either version 3 of the License, or
 (at your option) any later version.

 This program is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU Affero General Public License for more details.

 You should have received a copy of the GNU Affero General Public License
 along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

import org.junit.Test;

import de.lmu.ifi.dbs.elki.algorithm.AbstractSimpleAlgorithmTest;
import de.lmu.ifi.dbs.elki.data.DoubleVector;
import de.lmu.ifi.dbs.elki.database.Database;
import de.lmu.ifi.dbs.elki.result.outlier.OutlierResult;
import de.lmu.ifi.dbs.elki.utilities.ClassGenericsUtil;
import de.lmu.ifi.dbs.elki.utilities.optionhandling.parameterization.ListParameterization;

/**
 * Tests the AggarwalYuNaive algorithm.
 * 
 * @author Lucia Cichella
 * @since 0.4.0
 */
public class AggarwalYuNaiveTest extends AbstractSimpleAlgorithmTest {
  @Test
  public void testAggarwalYuNaive() {
    Database db = makeSimpleDatabase(UNITTEST + "outlier-3d-3clusters.ascii", 960);

    // Parameterization
    ListParameterization params = new ListParameterization();
    params.addParameter(AggarwalYuNaive.Parameterizer.K_ID, 2);
    params.addParameter(AggarwalYuNaive.Parameterizer.PHI_ID, 8);

    // setup Algorithm
    AggarwalYuNaive<DoubleVector> aggarwalYuNaive = ClassGenericsUtil.parameterizeOrAbort(AggarwalYuNaive.class, params);
    testParameterizationOk(params);

    // run AggarwalYuNaive on database
    OutlierResult result = aggarwalYuNaive.run(db);

    testAUC(db, "Noise", result, 0.9007777777777);
    testSingleScore(result, 945, -2.862640213982);
  }
}