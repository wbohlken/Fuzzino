//   Copyright 2012-2013 Fraunhofer FOKUS
//
//   Licensed under the Apache License, Version 2.0 (the "License");
//   you may not use this file except in compliance with the License.
//   You may obtain a copy of the License at
//
//       http://www.apache.org/licenses/LICENSE-2.0
//
//   Unless required by applicable law or agreed to in writing, software
//   distributed under the License is distributed on an "AS IS" BASIS,
//   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
//   See the License for the specific language governing permissions and
//   limitations under the License.
package de.fraunhofer.fokus.fuzzing.fuzzino.heuristics.generators.string;

import java.util.List;

import de.fraunhofer.fokus.fuzzing.fuzzino.heuristics.FuzzingHeuristic;
import de.fraunhofer.fokus.fuzzing.fuzzino.request.StringSpecification;
import de.fraunhofer.fokus.fuzzing.fuzzino.request.StringType;

/**
 * This is just a short cut for the generators BadLongStrings, BadStrings and
 * LongStrings.
 */
public class AllBadStringsGenerator extends ComposedStringGenerator {

	private static final long serialVersionUID = -2259863920514644374L;

	public AllBadStringsGenerator(StringSpecification stringSpec, long seed) {
		super(stringSpec, seed);
		this.owners.add(this);
		initHeuristics();
	}
	
	public AllBadStringsGenerator(List<FuzzingHeuristic> owners, long seed, StringSpecification stringSpec) {
		super(stringSpec, seed, owners);
		this.owners.add(this);
		initHeuristics();
	}
	
	private void initHeuristics() {
		heuristics.add(new BadLongStringsGenerator(owners, seed, stringSpec));
		heuristics.add(new BadStringsGenerator(stringSpec, seed, owners));
		heuristics.add(new LongStringsGenerator(owners, seed, stringSpec));
	}

	@Override
	public String getName() {
		return "AllBadStrings";
	}

	@Override
	public boolean canCreateValuesFor(StringSpecification spec) {
		boolean properType = spec.getType() == StringType.STRING || 
				             spec.getType() == StringType.SQL || 
				             spec.getType() == StringType.NUMBER;
		
		return properType;
	}
	
}
