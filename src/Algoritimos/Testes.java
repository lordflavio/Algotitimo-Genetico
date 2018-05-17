package Algoritimos;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;
import org.math.plot.Plot2DPanel;

public class Testes {

	public static void main(String[] args) {
		
		double[] r0 = new double[4];
		double[] r1 = new double[4];
		double[] r2 = new double[4];
		double[] r3 = new double[4];
		
		AlgotitimoG ag0 = new AlgotitimoG(0.7, 0.2);
		ag0.generateAG(150, 26, 26, 10, 2, 1);
		
//		AlgotitimoG ag1 = new AlgotitimoG(0.1, 0.6);
//		ag1.generateAG(150, 26, 26, 10, 2, 1);
//		
//		AlgotitimoG ag2 = new AlgotitimoG(0.1, 0.6);
//		ag2.generateAG(150, 26, 26, 10, 1, 2);
//		
//		AlgotitimoG ag3 = new AlgotitimoG(0.1, 0.6);
//		ag3.generateAG(150, 26, 26, 10, 1, 1);
		
		String[] regras = {"Media Geral Convergencia", "Media Geral dos Melhores Fittnes", "MediaGeral dos Fittnes","Media das Melhores Soluções"};
		
		 r0[0] = ag0.getMediaConvergencia();
		 r0[1] = ag0.getMediaBestFitness();
		 r0[2] = ag0.getFitnessMedio(); 
		 r0[3] = ag0.getFinalbestIndivido();
		 
//		 r1[0] = ag0.getMediaBestFitness();
//		 r1[1] = ag1.getMediaBestFitness();
//		 r1[2] = ag2.getMediaBestFitness(); 
//		 r1[3] = ag3.getMediaBestFitness();
//		 
//		 r2[0] = ag0.getFitnessMedio();
//		 r2[1] = ag1.getFitnessMedio();
//		 r2[2] = ag2.getFitnessMedio(); 
//		 r2[3] = ag3.getFitnessMedio();
//		 
//		 r3[0] = ag0.getFinalbestIndivido();
//		 r3[1] = ag1.getFinalbestIndivido();
//		 r3[2] = ag2.getFinalbestIndivido(); 
//		 r3[3] = ag3.getFinalbestIndivido();
		 
		 
	 chartBar ab = new chartBar("Taxa de Cruzamento: 0.7 | Taxa Mutação 0.2", r0, regras);
	 ab.pack( );        
     RefineryUtilities.centerFrameOnScreen( ab );        
     ab.setVisible( true ); 
     
//     chartBar abc = new chartBar("Media Geral dos Melhores Fittnes", r1, regras);
//	 abc.pack( );        
//     RefineryUtilities.centerFrameOnScreen( abc );        
//     abc.setVisible( true ); 
//     
//     chartBar abd = new chartBar("MediaGeral dos Fittnes", r2, regras);
//	 abd.pack( );        
//     RefineryUtilities.centerFrameOnScreen( abd );        
//     abd.setVisible( true ); 
//     
//     chartBar abe = new chartBar("Media das Melhores Soluções", r3, regras);
//	 abe.pack( );        
//     RefineryUtilities.centerFrameOnScreen( abe );        
//     abe.setVisible( true ); 

	}

}

class chartBar extends ApplicationFrame {

	public chartBar(String title,double[] value, String[] tecnicas) {
		super(title);
		
		JFreeChart barChart = ChartFactory.createBarChart(
				title,           
				"Caso de Uso",            
				"Media",            
				createDataset(value,tecnicas),          
				PlotOrientation.VERTICAL,           
				true, true, false);

		ChartPanel chartPanel = new ChartPanel( barChart );        
		chartPanel.setPreferredSize(new java.awt.Dimension( 1020 , 600 ) );        
		setContentPane( chartPanel ); 
	}
	
	 private CategoryDataset createDataset(final double[] bar1, final String[] tecnicas) {

	      final DefaultCategoryDataset dataset = 
	      new DefaultCategoryDataset( );  

	      dataset.addValue( bar1[0] , tecnicas[0] , "Convergência");
	      dataset.addValue( bar1[1] , tecnicas[1] , "Media Melhores Fitnnes" );
	      dataset.addValue( bar1[2] , tecnicas[2] , "Medial Geral Fitnnes" );
	      dataset.addValue( bar1[3] , tecnicas[3] , "Media Melhores Soluções" );

	      
//	      dataset.addValue( 5.0 , audi , speed );        
//	      dataset.addValue( 6.0 , audi , userrating );       
//	      dataset.addValue( 10.0 , audi , millage );        
//	      dataset.addValue( 4.0 , audi , safety );
//
//	      dataset.addValue( 4.0 , ford , speed );        
//	      dataset.addValue( 2.0 , ford , userrating );        
//	      dataset.addValue( 3.0 , ford , millage );        
//	      dataset.addValue( 6.0 , ford , safety );               

	      return dataset; 
	   }
	
}
