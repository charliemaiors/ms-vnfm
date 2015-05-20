package org.project.neutrino.nfvo.api;

import org.project.neutrino.nfvo.catalogue.nfvo.VimInstance;
import org.project.neutrino.nfvo.core.interfaces.VimManagement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author dbo
 *
 */
@RestController
@RequestMapping("/datacenters")
public class RestDatacenter {

//	TODO add log prints
//	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private VimManagement vimManagement;

	/**
	 * Adds a new VNF software Image to the datacenter repository
	 * 
	 * @param vimInstance
	 *            : Image to add
	 * @return datacenter: The datacenter filled with values from the core
	 */
	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public VimInstance create(@RequestBody @Valid VimInstance vimInstance) {
		return vimManagement.add(vimInstance);
	}

	/**
	 * Removes the VNF software Image from the Image repository
	 * 
	 * @param id
	 *            : The Image's id to be deleted
	 */
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") String id) {
		vimManagement.delete(id);
	}

	/**
	 * Returns the list of the VNF software images available
	 * 
	 * @return List<Image>: The list of VNF software images available
	 */
	@RequestMapping(method = RequestMethod.GET)
	public List<VimInstance> findAll() {
		return vimManagement.query();
	}

	/**
	 * This operation returns the VNF software image selected by id
	 * 
	 * @param id
	 *            : The id of the VNF software image
	 * @return image: The VNF software image selected
	 */
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public VimInstance findById(@PathVariable("id") String id) {
		VimInstance vimInstance = vimManagement.query(id);

		return vimInstance;
	}

	/**
	 * This operation updates the Network Service Descriptor (NSD)
	 * 
	 * @param new_vimInstance
	 *            : the new datacenter to be updated to
	 * @param id
	 *            : the id of the old datacenter
	 * @return VimInstance: the VimInstance updated
	 */

	@RequestMapping(value = "{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.ACCEPTED)
	public VimInstance update(@RequestBody @Valid VimInstance new_vimInstance,
			@PathVariable("id") String id) {
		return vimManagement.update(new_vimInstance, id);
	}
}
