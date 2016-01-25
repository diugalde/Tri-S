/*
	TRI-S - Web Service
	Developed by: Luis E. Ugalde Barrantes - Diego Ugalde Ávila. 2016.
	This code is licensed under the GNU GENERAL PUBLIC LICENSE (GPL) V3. See LICENSE file for details.
*/

package cr.ac.siua.tec.utils;


public interface PDFGeneratorFactory {

    /**
     * Factory method in charge of returning the corresponding PDFGenerator.
     */
    PDFGenerator getPDFGenerator(String formType);

}
