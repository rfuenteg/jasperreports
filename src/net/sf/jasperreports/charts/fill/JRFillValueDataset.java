/*
 * ============================================================================
 * GNU Lesser General Public License
 * ============================================================================
 *
 * JasperReports - Free Java report-generating library.
 * Copyright (C) 2001-2006 JasperSoft Corporation http://www.jaspersoft.com
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307, USA.
 *
 * JasperSoft Corporation
 * 303 Second Street, Suite 450 North
 * San Francisco, CA 94107
 * http://www.jaspersoft.com
 */
package net.sf.jasperreports.charts.fill;

import net.sf.jasperreports.charts.JRValueDataset;
import net.sf.jasperreports.engine.JRChartDataset;
import net.sf.jasperreports.engine.JRExpression;
import net.sf.jasperreports.engine.JRExpressionCollector;
import net.sf.jasperreports.engine.design.JRVerifier;
import net.sf.jasperreports.engine.fill.JRCalculator;
import net.sf.jasperreports.engine.fill.JRExpressionEvalException;
import net.sf.jasperreports.engine.fill.JRFillChartDataset;
import net.sf.jasperreports.engine.fill.JRFillObjectFactory;

import org.jfree.data.general.Dataset;
import org.jfree.data.general.DefaultValueDataset;

/**
 * @author Barry Klawans (bklawans@users.sourceforge.net)
 * @version $Id$
 */
public class JRFillValueDataset extends JRFillChartDataset implements JRValueDataset
{

	private Number value = null;

	/**
	 *
	 */
	private DefaultValueDataset valueDataset = new DefaultValueDataset();


	/**
	 *
	 */
	public JRFillValueDataset(JRValueDataset valueDataset,
							  JRFillObjectFactory factory)
	{
		super(valueDataset, factory);
	}

	/**
	 *
	 */
	public JRExpression getValueExpression()
	{
		return ((JRValueDataset)parent).getValueExpression();
	}


	/**
	 *
	 */
	protected void customInitialize()
	{
		valueDataset = new DefaultValueDataset();
	}

	/**
	 *
	 */
	protected void customEvaluate(JRCalculator calculator) throws JRExpressionEvalException
	{
		value = (Number)calculator.evaluate(getValueExpression());
	}

	/**
	 *
	 */
	protected void customIncrement()
	{
		valueDataset.setValue(value);
	}

	/**
	 *
	 */
	public Dataset getCustomDataset()
	{
		return valueDataset;
	}

	/**
	 *
	 */
	public Object getLabelGenerator()
	{
		return null;
	}

	/**
	 *
	 */
	public byte getDatasetType() {
		return JRChartDataset.VALUE_DATASET;
	}

	/**
	 *
	 */
	public void collectExpressions(JRExpressionCollector collector)
	{
		collector.collect(this);
	}

	public void validate(JRVerifier verifier)
	{
		verifier.verify(this);
	}

}
