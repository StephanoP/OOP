package dataset;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

//TO DO: extends this to accept any number or object
//extends the class that puts a csv file with integer entries to 
//treat the NaiveBayesClass variables
public class ParseLearnCSV extends ParseCSV {
	
	protected VariableNode[] VariableList;
	protected ClassifierNode ClassN;
	
	ParseLearnCSV(){
		super();
	}
	
	ParseLearnCSV(String filename){
		super();
		parse(filename);
	}
	
	public void parse(String filename){
		
		
		FileReader toParse;
		try {
			toParse = new FileReader(filename);
			
			/*************************************************************************************/
			/* this was outside try/catch block before*/
			
			BufferedReader br = null;
			String line;
			String cvsSplitBy = ",";
			
			try {
				br = new BufferedReader(toParse);
				
				String[] lineparse;
				int[] temp;

				/* if first line not null */
				if((line = br.readLine()) != null){
					
					lineparse = line.split(cvsSplitBy); /* split string by commas */
					colSize = lineparse.length; /* number of variables + 1 (class variable)*/
					firstLine(lineparse);				
				}
					
					/* while there are other lines*/
					while ((line = br.readLine()) != null) {

					    // use comma as separator
						//TO DO: VERIFICAR QUE TODAS AS LINHAS TEM O MESMO TAMANHO(NCOLUMS)
						lineparse = line.split(cvsSplitBy); /* split string by commas */
						temp = new int[colSize];
						for(int j=0 ; j<colSize;j++){
							temp[j] = Integer.parseInt(lineparse[j]);
							middleLine(temp[j], j);
						}
						parsedData.add(temp);

					}
			//TO DO: BETTER TREATMENT TO EXCEPTION
			} catch (FileNotFoundException e) {
					e.printStackTrace();
			} catch (IOException e) {
					e.printStackTrace();
			} finally {
				if (br != null) {
					try {
						br.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
			/*************************************************************************************/
			
			
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	//by default the first line is ignored, only used to know column size
	//can be extended to not ignore first line, just by redefining method to do something
	public void firstLine(String[] line, int colSize){
		
		VariableList = new VariableNode[colSize];

		/* go through all strings in line (seperated by commas)*/
		for(int j=0 ; j < colSize-1 ; j++){
			
			VariableList[j] = new VariableNode(line[j], j);
		}
	}
	
	
	public VariableNode[] getVariableList(){
		return this.VariableList;
	}
	
	public ClassifierNode getClassVariable(){
		return this.ClassN;
	}
	
	

}
