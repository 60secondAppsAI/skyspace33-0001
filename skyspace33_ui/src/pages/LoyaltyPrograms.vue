<template>
  <div class="content">
    <div class="row">
      <div class="col-12">
        <card class="card-plain">
          <!-- <template slot="header">
            <h4 class="card-title">Table on Plain Background</h4>
            <p class="category">Here is a subtitle for this table</p>
          </template>-->
          <div class="table-full-width text-left">
            <loyaltyProgram-table
            v-if="loyaltyPrograms && loyaltyPrograms.length > 0"
				:title="table1.title"
				:sub-title="table1.subTitle"
				:loyaltyPrograms="loyaltyPrograms"
				:totalElements="totalElements"
				:page="page"
				:columns="table1.columns"
 				@get-all-loyalty-programs="getAllLoyaltyPrograms"
             >

            </loyaltyProgram-table>
          </div>
        </card>
      </div>

    </div>
  </div>
</template>
<script>
import { Card } from "@/components/Card";

import LoyaltyProgramTable from "@/components/LoyaltyProgramTable";
import LoyaltyProgramService from "../services/LoyaltyProgramService";

const tableColumns = [];
const tableData = [
];

export default {
  components: {
    Card,
    LoyaltyProgramTable,
  },
  data() {
    return {
      loyaltyPrograms: [],
	  totalElements: 0,
      page: 0,
      searchQuery: '',     
      table1: {
        title: "Simple Table",
        columns: [...tableColumns],
        data: [...tableData],
      },
    };
  },
  methods: {
    async getAllLoyaltyPrograms(sortBy='loyaltyProgramId',sortOrder='true',page="0",size="50") {
      try {
        try {
			const searchDTO = {
				sortBy: sortBy, 
				sortOrder: sortOrder, 
				searchQuery: this.searchQuery,
				page: page, 
				size: size
			};
	        let response = await LoyaltyProgramService.getAllLoyaltyPrograms(searchDTO);
	        if (!response.data.empty) {
		        if (response.data.loyaltyPrograms.length) {
					this.loyaltyPrograms = response.data.loyaltyPrograms;
				}
      			this.totalElements = response.data.totalElements;
      			this.page = response.data.page;
	        }
        } catch (error) {
          console.error("Error fetching loyaltyPrograms:", error);
        }
        
      } catch (error) {
        console.error("Error fetching loyaltyProgram details:", error);
      }
    },
  },
  mounted() {
    this.getAllLoyaltyPrograms();
  },
  created() {
    this.$root.$on('searchQueryForLoyaltyProgramsChanged', (searchQuery) => {
    	this.searchQuery = searchQuery;
    	this.getAllLoyaltyPrograms();
    })
  }
};
</script>
<style></style>
