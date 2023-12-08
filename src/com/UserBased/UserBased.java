package com.UserBased;

import java.io.File;
import java.util.List;

import org.apache.mahout.cf.taste.impl.common.LongPrimitiveIterator;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.ThresholdUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericItemBasedRecommender;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.LogLikelihoodSimilarity;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.impl.similarity.UncenteredCosineSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.UserBasedRecommender;
import org.apache.mahout.cf.taste.similarity.ItemSimilarity;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;

public class UserBased {

	public static void main(String[] args) throws Exception{

		DataModel model = new FileDataModel(new File("data/rates.csv"));

		UserSimilarity similarity = new PearsonCorrelationSimilarity(model);
		ItemSimilarity sim = new LogLikelihoodSimilarity(model);

		GenericItemBasedRecommender recom = new GenericItemBasedRecommender(model, sim);


			List<RecommendedItem> recommendations = recom.mostSimilarItems(9, 50);
			for(RecommendedItem recommendation : recommendations)
			{
				System.out.print("[9 ,"+recommendation.getItemID()+"] : "+recommendation.getValue()+" \n ");
			}
			System.out.println("\n");


		UserNeighborhood neighborhood = new ThresholdUserNeighborhood(0.1 , similarity, model);
		UserBasedRecommender recommender = new GenericUserBasedRecommender(model, neighborhood, similarity);
		long[] neighbors = neighborhood.getUserNeighborhood(277157);
		  System.out.println("Neighbors for user 277157 are");
		  int count=0;
		  for (long user : neighbors) {
		   count++;
		   System.out.print("neighbor`s user 277157: "+ user + "\n");
		  }

		/*List<RecommendedItem> recommendations = recommender.recommend(277157,count);
		for(RecommendedItem recommendation : recommendations) {
		  System.out.println(recommendation);
		}*/
	     
		
	}

}
