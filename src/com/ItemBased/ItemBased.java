package com.ItemBased;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.mahout.cf.taste.impl.common.LongPrimitiveIterator;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.recommender.GenericItemBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.LogLikelihoodSimilarity;
import org.apache.mahout.cf.taste.impl.similarity.TanimotoCoefficientSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.similarity.ItemSimilarity;

public class ItemBased {

	public static void main(String[] args) throws Exception {
		
		DataModel dm = new FileDataModel(new File("data/rates.csv"));
		
		ItemSimilarity sim = new LogLikelihoodSimilarity(dm);
		//TanimotoCoefficientSimilarity sim = new TanimotoCoefficientSimilarity(dm);//
		
		GenericItemBasedRecommender recommender = new GenericItemBasedRecommender(dm, sim);
		
		int x=1;
		for(LongPrimitiveIterator items = dm.getItemIDs(); items.hasNext();) {
			long itemID = items.nextLong();
			List<RecommendedItem> recommendations = recommender.mostSimilarItems(itemID, 5);//پنج تا شبیه ترین آیتم به آیتم مورد نظر
			for(RecommendedItem recommendation : recommendations)
			{
				System.out.print("["+itemID+","+recommendation.getItemID()+"] : "+recommendation.getValue()+" \n ");
			}
			System.out.println("\n");
			x++;
			if(x>1000) System.exit(1); //چند تا آیتم اول با بقیه آیتم ها مقایسه شود
		}
	}

}
