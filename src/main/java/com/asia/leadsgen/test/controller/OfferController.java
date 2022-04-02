package com.asia.leadsgen.test.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@RequestMapping(path = "/offers")
public class OfferController {
//	@Autowired
//	OfferService offerService;
//
//	@GetMapping("/{id}")
//	public ResponseEntity<FmerchCampaignInfo> getDetailOffer(
//			@RequestHeader(name = "x-authorization", required = true) String accessToken,
//			@RequestAttribute(name = "user_info", required = true) UserInfo userInfo, @PathVariable String id) {
//		FmerchCampaignInfo result = offerService.getOfferById(id, userInfo.getUserId());
//		return new ResponseEntity<>(result, HttpStatus.OK);
//	}
//
//	@PostMapping()
//	public ResponseEntity<OfferInfo> createOffer(
//			@RequestHeader(name = "x-authorization", required = true) String accessToken,
//			@RequestAttribute(name = "user_info", required = true) UserInfo userInfo, @RequestBody OfferInfo offerInfo)
//			throws SQLException {
//
//		OfferInfo result = offerService.createOffer(userInfo, offerInfo);
//
//		return new ResponseEntity<>(result, HttpStatus.CREATED);
//	}
//
//	@GetMapping("/v2")
//	@Operation(summary = "List Offers v2")
//	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "OK", content = @Content),
//			@ApiResponse(responseCode = "201", description = "Not implement", content = @Content),
//			@ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
//			@ApiResponse(responseCode = "403", description = "Not implement", content = @Content),
//			@ApiResponse(responseCode = "404", description = "Not Found", content = @Content),
//			@ApiResponse(responseCode = "500", description = "Internal Sever Error", content = @Content) })
//	public ResponseEntity<ResultObjectInfo<FmerchCampaignResponse>> getOfferv3(
//			@RequestHeader(name = "x-authorization", required = true) String accessToken,
//			@RequestAttribute(name = "user_info", required = true) UserInfo userInfo,
//			@RequestParam(name = "page", defaultValue = "1") int page,
//			@RequestParam(name = "page_size", defaultValue = "50") int pageSize,
//			@RequestParam(name = "status", required = false) String status,
//			@RequestParam(name = "start_date", required = false) String startDate,
//			@RequestParam(name = "end_date", required = false) String endDate,
//			@RequestParam(name = "search", required = false) String search,
//			@RequestParam(name = "sort", defaultValue = "payout") String sort,
//			@RequestParam(name = "dir", defaultValue = "desc") String dir) throws SQLException, ParseException {
//
//		logger.info("User Info: " + userInfo);
//
//		ResultObjectInfo<FmerchCampaignResponse> resultObject = offerService.listOfferv2(userInfo.getUserId(), page,
//				pageSize, status, startDate, endDate, search, sort, dir);
//
//		return new ResponseEntity<>(resultObject, HttpStatus.OK);
//	}
//
//	private Logger logger = Logger.getLogger(SimpleController.class.getName());
}
